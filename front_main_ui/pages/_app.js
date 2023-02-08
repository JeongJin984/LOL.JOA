import App from 'next/app'
import '../styles/globals.css'
import 'semantic-ui-css/semantic.min.css'
import Gnb from "../src/component/Gnb";
import Footer from "../src/component/Footer";
import wrapper from "../src/store/store-wrapper";
import { CookiesProvider } from "react-cookie";

class MyApp extends App {
  static getInitialProps = wrapper.getInitialAppProps(store => async ({ Component, ctx }) => {

    return {
      pageProps: {
        // Call page-level getInitialProps
        // DON'T FORGET TO PROVIDE STORE TO PAGE
        ...(Component.getInitialProps ? await Component.getInitialProps({ ...ctx, store }) : {}),
        // Some custom thing for all pages
        pathname: ctx.pathname,
      },
    };
  });

  render() {
    const { Component, pageProps } = this.props;

    return (
      <CookiesProvider>
        <div style={{ backgroundColor: "#F9F9F9" }}>
          <Gnb />
          <Component {...pageProps} />
          <Footer />
        </div >
      </CookiesProvider>
    );
  }
}

// function MyApp({ Component, pageProps }) {
//   return (
//     <div style={{ backgroundColor: "#F9F9F9" }}>
//       <Top />
//       <Gnb />
//       <Component {...pageProps} />
//       <Footer />
//     </div >
//   )
// }
// export default MyApp
export default wrapper.withRedux(MyApp);
