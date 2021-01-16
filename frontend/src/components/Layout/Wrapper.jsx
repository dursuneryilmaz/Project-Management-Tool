/* eslint-disable jsx-a11y/anchor-is-valid */
import Sidebar from "./Sidebar";
import Footer from "./Footer";
import ScrollTop from "./ScrollTop";
import LogoutModal from "./LogoutModal";
import Header from "./Header";

const Wrapper = (props) => {
  return (
    <div>
      <div id="wrapper">
        <Sidebar />
        <div id="content-wrapper" className="d-flex flex-column">
          <div id="content">
            {/* Header Starts */}
            <Header />
            {/* Begin Page Content */}
            <div className="container-fluid">{props.children}</div>
          </div>
          <Footer />
        </div>
      </div>
      <ScrollTop />
      <LogoutModal />
    </div>
  );
};
export default Wrapper;
