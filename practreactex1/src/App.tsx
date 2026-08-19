import {Route, Routes} from "react-router-dom";
import Registro from "./pages/Registro.tsx";
import Resumen from "./pages/Resumen.tsx";


function App() {

  return (
      <>
          <Routes>
              <Route path={"/"} element={<Registro/>}/>
              <Route path={"/resumen"} element={<Resumen/>}/>
          </Routes>
      </>
  )
}

export default App
