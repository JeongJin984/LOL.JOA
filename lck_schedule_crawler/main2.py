import os

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

import json
import boto3
from botocore.client import Config

chrome_options = Options()
chrome_options.add_argument("--headless")   # Hides the browser window

driver = webdriver.Remote(
    command_executor=os.environ['SELENIUM_SERVER_IP'],
    options=chrome_options
)

# driver = webdriver.Chrome()
driver.get(os.environ['CRAWLING_TARGET_URL'])

span = driver.find_element(by=By.ID, value="matchlist-show-all")
span.click()

divs = driver.find_elements(by=By.CLASS_NAME, value="matchlist-tab-wrapper")


def get_data(tbody):
    result = {}

    schedule_div = tbody.find_elements(by=By.CLASS_NAME, value="matchlist-you-date")
    spans = tbody.find_elements(by=By.CLASS_NAME, value="team")

    i1 = 0
    i2 = 0
    while i2 < len(spans):
        date = schedule_div[i1].find_element(by=By.XPATH, value="./td/span").text
        i1 += 1

        team_span = spans[i2].find_elements(by=By.XPATH, value="./span")
        left_team_name_1 = team_span[0].text
        left_team_logo_1 = team_span[1].find_element(by=By.XPATH, value="./img").get_attribute("data-src")
        i2 += 1

        team_span = spans[i2].find_elements(by=By.XPATH, value="./span")
        right_team_name_1 = team_span[1].text
        right_logo_name_1 = team_span[0].find_element(by=By.XPATH, value="./img").get_attribute("data-src")
        i2 += 1

        result[date] = [{
            "left_team_name": left_team_name_1,
            "left_team_logo": left_team_logo_1,
            "right_team_name": right_team_name_1,
            "right_logo_name": right_logo_name_1,
        }]

        if len(spans) > i2:
            team_span = spans[i2].find_elements(by=By.XPATH, value="./span")
            left_team_name_2 = team_span[0].text
            left_team_logo_2 = team_span[1].find_element(by=By.XPATH, value="./img").get_attribute("data-src")
            i2 += 1

            team_span = spans[i2].find_elements(by=By.XPATH, value="./span")
            right_team_name_2 = team_span[1].text
            right_logo_name_2 = team_span[0].find_element(by=By.XPATH, value="./img").get_attribute("data-src")
            i2 += 1

            result[date].append({
                "left_team_name": left_team_name_2,
                "left_team_logo": left_team_logo_2,
                "right_team_name": right_team_name_2,
                "right_logo_name": right_logo_name_2,
            })
    return result


ACCESS_KEY_ID = os.environ['ACCESS_KEY_ID']
ACCESS_SECRET_KEY = os.environ['SECRET_ACCESS_KEY']
BUCKET_NAME = os.environ['BUCKET_NAME']
def upload_to_S3(file_name):
    data = open(file_name, 'rb')
    s3 = boto3.resource(
        's3',
        aws_access_key_id=ACCESS_KEY_ID,
        aws_secret_access_key=ACCESS_SECRET_KEY,
        config=Config(signature_version='s3v4')
    )
    s3.Bucket(BUCKET_NAME).put_object(
        Key=file_name, Body=data, ContentType='text/json')


def main():
    result = {}
    for div in divs:
        tbody = div.find_element(by=By.XPATH, value="./table/tbody")
        week_num = tbody.find_element(by=By.XPATH, value="./tr").find_element(by=By.XPATH, value="./th").text \
            .split("\n")[1]
        result[week_num] = get_data(tbody)

    print(result)
    print("json!!!")
    with open("schedule.json", "w") as json_file:
        json.dump(result, json_file)
    upload_to_S3("schedule.json")


main()
