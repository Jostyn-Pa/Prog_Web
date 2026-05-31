
import './App.css'
import Login from "./pages/Login.tsx";
import {Route, Routes} from "react-router-dom";
import PanelUsuario from "./pages/PanelUsuario.tsx";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/panel/:name" element={<PanelUsuario/>}/>
      </Routes>
    </>
  )
}

export default App
