/* eslint-disable jsx-a11y/alt-text */
import React, { Component } from "react";
import AlertBox from "./Header/AlertBox";
import MessageBox from "./Header/MessageBox";
import SearchBox from "./Header/SearchBox";
import UserBox from "./Header/UserBox";

class Header extends Component {
  render() {
    return (
      <div>
        {/* Header Nav Start */}
        <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
          {/* Search Box */}
          <SearchBox />
          <ul className="navbar-nav ml-auto">
            {/* alerts box */}
            <AlertBox />
            {/* message box */}
            <MessageBox />
            <div className="topbar-divider d-none d-sm-block"></div>
            {/* User box */}
            <UserBox />
          </ul>
        </nav>
      </div>
    );
  }
}

export default Header;
