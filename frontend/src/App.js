import "./App.css";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AdminPortal from "./pages/AdminPortal";

import Home from "./pages/Home";
import Login from "./components/Login";

function App() {
  return(
    <Router>
      <Routes>
        <Route  path="/" element={<Home/>}/>
        <Route  path="/login" element={<Login/>}/>
        <Route  path="/admin/dashboard" element={<AdminPortal/>}/>
      </Routes>

    </Router>
  )
}

export default App;
