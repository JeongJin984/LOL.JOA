import Link from "next/link"
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import { useDispatch, useSelector } from "react-redux";
import { Segment, Button } from 'semantic-ui-react'
import styles from '../../styles/Gnb.module.css';

const Gnb = () => {

  const router = useRouter();

  const dispatch = useDispatch();

  const [cookies, setCookie] = useCookies(['user']);

  const { user } = useSelector(state => state.userReducer)

  const onClickLogout = () => {
    if (user.username !== undefined) {
      setCookie("SUID", undefined, { path: "/" })
      router.push("/")
    }
  }


  const LoginButton = () => {
    if (user.username !== undefined) {
      return (
        <div style={{ display: "flex" }}>
          <div style={{ flex: 1, color: "white" }}>
            <div>
              ID : {user.username}
            </div>
            <div style={{ marginRight: "5px" }}>
              POINT : {user.point}
            </div>
          </div>
          <Button onClick={onClickLogout}>
            로그아웃
          </Button>
        </div>
      )
    }
    else {
      return (
        <Button href={"/login"}>
          로그인
        </Button>
      )
    }
  }

  return (
    <div>
      <div className={styles.navbarWeb} >
        <Link href="/" className={styles.logo}>
          <a style={{ color: "white" }}>
            <h1>LOLjoa</h1>
          </a>
        </Link>
        <ul className={styles.links}>
          <li className={styles.navlink}>
            <Link href="/schedule">경기 일정</Link>
          </li>
          <li className={styles.navlink}>
            <Link href="/information">팀 소개</Link>
          </li>
          <li className={styles.navlink}>
            <Link href="/board">게시판</Link>
          </li>
          <li className={styles.navlink}>
            <Link href="/management">관리자 페이지</Link>
          </li>
        </ul>
        <LoginButton />
      </div>
    </div>
  )
}

export default Gnb