import "./App.css";
import DashboardWrapper from "./components/Dashboard/Dashboard";
import Wrapper from "./components/Layout/Wrapper";

function App() {
  return (
    <div className="App">
      <Wrapper>
        <DashboardWrapper />
      </Wrapper>
    </div>
  );
}

export default App;
