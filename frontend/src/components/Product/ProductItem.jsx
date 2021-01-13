import React, { Component } from "react";

export default class ProductItem extends Component {
  render() {
    return (
      <div className="card">
        <div className="card-header">
          <h5 className="card-title m-0">Product Name</h5>
        </div>
        <div className="card-body">
          <h6 className="card-title">Product Company</h6>

          <p className="card-text">
            Product descriptions with some informations about product.
          </p>

          <button type="button" class="btn btn-secondary toastrDefaultSuccess">
            Pre Game
          </button>
          <button type="button" class="btn btn-secondary toastrDefaultSuccess">
            Game
          </button>
          <button type="button" class="btn btn-secondary toastrDefaultSuccess">
            Post Game
          </button>
        </div>
      </div>
    );
  }
}
