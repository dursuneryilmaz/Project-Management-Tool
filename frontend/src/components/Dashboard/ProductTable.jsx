import React, { Component } from "react";

class ProductTable extends Component {
  render() {
    return (
      <div>
        <div className="row">
          {/* <!-- Product List -->*/}
          <div className="col-xl-12 col-lg-12">
            <div className="card shadow mb-4">
              <div className="card-header py-3">
                <h6 className="m-0 font-weight-bold text-primary">
                  Product List
                </h6>
              </div>
              <div className="card-body">
                <div className="table-responsive">
                  <div
                    id="dataTable_wrapper"
                    className="dataTables_wrapper dt-bootstrap4"
                  >
                    <div className="row">
                      <div className="col-sm-12">
                        <table
                          className="table table-bordered dataTable"
                          id="dataTable"
                          width="100%"
                          cellSpacing="0"
                          role="grid"
                          aria-describedby="dataTable_info"
                        >
                          <thead>
                            <tr role="row">
                              <th
                                className="sorting_asc"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-sort="ascending"
                                aria-label="Name: activate to sort column descending"
                              >
                                Name
                              </th>
                              <th
                                className="sorting"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-label="Position: activate to sort column ascending"
                              >
                                Position
                              </th>
                              <th
                                className="sorting"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-label="Office: activate to sort column ascending"
                              >
                                Office
                              </th>
                              <th
                                className="sorting"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-label="Age: activate to sort column ascending"
                              >
                                Age
                              </th>
                              <th
                                className="sorting"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-label="Start date: activate to sort column ascending"
                              >
                                Start date
                              </th>
                              <th
                                className="sorting"
                                tabIndex="0"
                                aria-controls="dataTable"
                                rowSpan="1"
                                colSpan="1"
                                aria-label="Salary: activate to sort column ascending"
                              >
                                Salary
                              </th>
                            </tr>
                          </thead>
                          <tfoot>
                            <tr>
                              <th rowSpan="1" colSpan="1">
                                Name
                              </th>
                              <th rowSpan="1" colSpan="1">
                                Position
                              </th>
                              <th rowSpan="1" colSpan="1">
                                Office
                              </th>
                              <th rowSpan="1" colSpan="1">
                                Age
                              </th>
                              <th rowSpan="1" colSpan="1">
                                Start date
                              </th>
                              <th rowSpan="1" colSpan="1">
                                Salary
                              </th>
                            </tr>
                          </tfoot>
                          <tbody>
                            <tr role="row" className="odd">
                              <td className="sorting_1">Airi Satou</td>
                              <td>Accountant</td>
                              <td>Tokyo</td>
                              <td>33</td>
                              <td>2008/11/28</td>
                              <td>$162,700</td>
                            </tr>
                            <tr role="row" className="even">
                              <td className="sorting_1">Angelica Ramos</td>
                              <td>Chief Executive Officer (CEO)</td>
                              <td>London</td>
                              <td>47</td>
                              <td>2009/10/09</td>
                              <td>$1,200,000</td>
                            </tr>
                            <tr role="row" className="odd">
                              <td className="sorting_1">Ashton Cox</td>
                              <td>Junior Technical Author</td>
                              <td>San Francisco</td>
                              <td>66</td>
                              <td>2009/01/12</td>
                              <td>$86,000</td>
                            </tr>
                            <tr role="row" className="even">
                              <td className="sorting_1">Bradley Greer</td>
                              <td>Software Engineer</td>
                              <td>London</td>
                              <td>41</td>
                              <td>2012/10/13</td>
                              <td>$132,000</td>
                            </tr>
                            <tr role="row" className="odd">
                              <td className="sorting_1">Brenden Wagner</td>
                              <td>Software Engineer</td>
                              <td>San Francisco</td>
                              <td>28</td>
                              <td>2011/06/07</td>
                              <td>$206,850</td>
                            </tr>
                            <tr role="row" className="even">
                              <td className="sorting_1">Brielle Williamson</td>
                              <td>Integration Specialist</td>
                              <td>New York</td>
                              <td>61</td>
                              <td>2012/12/02</td>
                              <td>$372,000</td>
                            </tr>
                            <tr role="row" className="odd">
                              <td className="sorting_1">Bruno Nash</td>
                              <td>Software Engineer</td>
                              <td>London</td>
                              <td>38</td>
                              <td>2011/05/03</td>
                              <td>$163,500</td>
                            </tr>
                            <tr role="row" className="even">
                              <td className="sorting_1">Caesar Vance</td>
                              <td>Pre-Sales Support</td>
                              <td>New York</td>
                              <td>21</td>
                              <td>2011/12/12</td>
                              <td>$106,450</td>
                            </tr>
                            <tr role="row" className="odd">
                              <td className="sorting_1">Cara Stevens</td>
                              <td>Sales Assistant</td>
                              <td>New York</td>
                              <td>46</td>
                              <td>2011/12/06</td>
                              <td>$145,600</td>
                            </tr>
                            <tr role="row" className="even">
                              <td className="sorting_1">Cedric Kelly</td>
                              <td>Senior Javascript Developer</td>
                              <td>Edinburgh</td>
                              <td>22</td>
                              <td>2012/03/29</td>
                              <td>$433,060</td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* Product item end */}
          </div>
        </div>
      </div>
    );
  }
}

export default ProductTable;
