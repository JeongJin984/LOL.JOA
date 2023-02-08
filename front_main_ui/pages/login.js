import { Button, Form } from "semantic-ui-react"
import { useRouter } from "next/router";
import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { LOGIN_REQUEST } from "../config/event/eventName/userEvent";
import { useCookies } from "react-cookie";

const Login = () => {
  const router = useRouter()
  const dispatch = useDispatch();

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [cookies, setCookie] = useCookies(['user']);

  const { user } = useSelector(state => state.userReducer)

  useEffect(() => {
    if (user.username !== undefined) {
      setCookie("SUID", user.username, { path: "/" })
      router.push("/")
        .then(() => alert("login success"))
    }
  }, [user])


  const onChangeUsername = useCallback((e) => {
    console.log(e.target.value)
    setUsername(e.target.value)
  }, [])

  const onChangePassword = useCallback((e) => {
    setPassword(e.target.value)
  }, [])

  const onClickLogin = useCallback(() => {
    dispatch({
      type: LOGIN_REQUEST,
      params: {
        username,
        password
      }
    })
  }, [username, password])

  return (
    <div style={{ padding: "100px 0", textAlign: "center" }}>
      <Form>
        <Form.Field inline>
          <input placeholder="ID" onChange={onChangeUsername} value={username} />
        </Form.Field>
        <Form.Field inline>
          <input type="password" placeholder="Password" onChange={onChangePassword} value={password} />
        </Form.Field>
        <Button onClick={onClickLogin}>Login</Button>
      </Form>
    </div>
  )

}

export default Login