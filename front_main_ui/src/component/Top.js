import Link from 'next/link'
import styles from '../../styles/Header.module.css';

const Top = () => {
  return (
    <header className={styles.header}>
      <ul className={styles.list}>
        <li>고객센터</li>
        <li>사이트맵</li>
        <li>
          <Link href={"/login"}>
            <div>
              로그인
            </div>
          </Link>
        </li>
      </ul>
    </header>
  );
};

export default Top