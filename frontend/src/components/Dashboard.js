import React, { Component } from "react";
import ProductItem from "./Product/ProductItem";

export default class Dashboard extends Component {
  render() {
    return (
      <div class="projects">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="content-header">
                <div class="row mb-2">
                  <div class="col-sm-6">
                    <h1 class="m-0 text-dark"> Products</h1>
                  </div>
                </div>
              </div>
              <br />
              <a href="ProjectForm.html" class="btn btn-lg btn-info">
                Create a Product
              </a>
              <br />
              <hr />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
              <ProductItem />
              <br />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
