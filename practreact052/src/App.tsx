import {Route, Routes} from "react-router-dom";
import FormularioAdopcion from "./pages/FormularioAdopcion.tsx";
import Resumen from "./pages/Resumen.tsx";

function App() {

  // @ts-ignore
  return (
    <>
      <Routes>
        <Route path={"/"} element={<FormularioAdopcion/>}/>
        <Route path={"/resumen"} element={<Resumen/>}/>
      </Routes>
    </>
  )
}

export default App
