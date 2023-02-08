import selenium
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import json

driver = webdriver.Chrome()
driver.get("https://lol.fandom.com/wiki/LCK/2022_Season/Spring_Season/Match_History")
trs = driver.find_elements(by=By.CLASS_NAME, value="multirow-highlighter")

result = {}
for tr in trs:
    tds = tr.find_elements(by=By.XPATH, value="./td")

    result[tds[0].text] = []
    result[tds[0].text].append({
        "patch_version": tds[1].find_element(by=By.XPATH, value="./a").text,
        "team1_logo_image": tds[2].find_element(by=By.XPATH, value="./a/img").get_attribute('src'),
        "team1_name": tds[2].find_element(by=By.XPATH, value="./a").get_attribute('title'),
        "team2_logo_image": tds[3].find_element(by=By.XPATH, value="./a/img").get_attribute('src'),
        "team2_name": tds[3].find_element(by=By.XPATH, value="./a").get_attribute('title'),
        "winner_logo_image": tds[4].find_element(by=By.XPATH, value="./a/img").get_attribute('src'),
        "winner_name": tds[4].find_element(by=By.XPATH, value="./a").get_attribute('title'),
        "team1_ban_list": [],
        "team2_ban_list": [],
        "team1_pick_list": [],
        "team2_pick_list": [],
        "team1_roster": [],
        "team2_roster": [],

    })

    spans = tds[5].find_elements(by=By.XPATH, value="./span")
    for span in spans:
        result[tds[0].text][-1]["team1_ban_list"].append(span.get_attribute("title"))
    spans = tds[6].find_elements(by=By.XPATH, value="./span")
    for span in spans:
        result[tds[0].text][-1]["team2_ban_list"].append(span.get_attribute("title"))
    spans = tds[7].find_elements(by=By.XPATH, value="./span")
    for span in spans:
        result[tds[0].text][-1]["team1_pick_list"].append(span.get_attribute("title"))
    spans = tds[8].find_elements(by=By.XPATH, value="./span")
    for span in spans:
        result[tds[0].text][-1]["team2_pick_list"].append(span.get_attribute("title"))
    aTags = tds[9].find_elements(by=By.XPATH, value="./a")
    for a in aTags:
        result[tds[0].text][-1]["team1_roster"].append(a.text)
    aTags = tds[10].find_elements(by=By.XPATH, value="./a")
    for a in aTags:
        result[tds[0].text][-1]["team2_roster"].append(a.text)
    print("done!")

    # print(tds[0].text)
    # print(tds[1].find_element(by=By.XPATH, value="./a").text)
    # print(tds[2].find_element(by=By.XPATH, value="./a/img").get_attribute('src'))
    # print(tds[2].find_element(by=By.XPATH, value="./a").get_attribute('title'))
    # print(tds[3].find_element(by=By.XPATH, value="./a/img").get_attribute('src'))
    # print(tds[3].find_element(by=By.XPATH, value="./a").get_attribute('title'))
    # print(tds[4].find_element(by=By.XPATH, value="./a/img").get_attribute('src'))
    # print(tds[4].find_element(by=By.XPATH, value="./a").get_attribute('title'))
    # spans = tds[5].find_elements(by=By.XPATH, value="./span")
    # for span in spans:
    #     print(span.get_attribute("title"))
    # spans = tds[6].find_elements(by=By.XPATH, value="./span")
    # for span in spans:
    #     print(span.get_attribute("title"))
    # spans = tds[7].find_elements(by=By.XPATH, value="./span")
    # for span in spans:
    #     print(span.get_attribute("title"))
    # spans = tds[8].find_elements(by=By.XPATH, value="./span")
    # for span in spans:
    #     print(span.get_attribute("title"))
    # aTags = tds[9].find_elements(by=By.XPATH, value="./a")
    # for a in aTags:
    #     print(a.text)
    # aTags = tds[10].find_elements(by=By.XPATH, value="./a")
    # for a in aTags:
    #     print(a.text)

print("json start!!")
for time in result:
    with open(time + ".json", "w") as json_file:
        json.dump(result, json_file)
