import {Link, Route, Routes} from "react-router-dom";
import Home from "./pages/Home.tsx";
import Contact from "./pages/Contact.tsx";

function App() {

  return (
    <>
        <nav>
            <Link to="/home">Home</Link>
            |
            <Link to="/contact/test1">Contact</Link>
            |
            <Link to="contact2/test2">Contact2</Link>
        </nav>
        <Routes>
            <Route path="/home" element={<Home/>}/>
            <Route path="contact/:name" element={<Contact/>}/>
            <Route path="contact2/:name" element={<Contact/>}/>
        </Routes>
    </>
  )
}

export default App
