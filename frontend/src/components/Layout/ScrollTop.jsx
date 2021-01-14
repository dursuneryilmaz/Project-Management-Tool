import React, { Component } from "react";

class ScrollTop extends Component {
  render() {
    return (
      <div>
        {/*Scroll to Top Button*/}
        <a className="scroll-to-top rounded" href="#page-top">
          <i className="fas fa-angle-up"></i>
        </a>
      </div>
    );
  }
}

export default ScrollTop;
