import {Route, Routes} from "react-router-dom";
import Boleto from "./pages/Boleto.tsx";
import FormularioVuelo from "./pages/FormularioVuelo.tsx";

function App() {

  return (
    <>
      <Routes>
        <Route path={"/"} element={<FormularioVuelo/>}/>
        <Route path={"/boleto"} element={<Boleto/>}/>
      </Routes>
    </>
  )
}

export default App
