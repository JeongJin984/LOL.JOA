import Link from "next/link"

const Board = () => {
  return (
    <div>
      <div style={{ textAlign: "center", margin: "30px" }}>
        <h1>Board_main</h1>
      </ div >
      <div style={{ display: "flex", justifyContent: "center" }}>
        <ol>
          <li>
            <Link href="http://localhost:3000/boards/aaa">aaa</Link>
          </li>
          <li>
            <Link href="http://localhost:3000/boards/bbb">bbb</Link>
          </li>
        </ol>
      </div>
    </div>

  )
}

export default Board