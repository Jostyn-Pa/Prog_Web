import ListaGeneralFetch from "./pages/ListaGeneralFetch.tsx";
import {Route, Routes} from "react-router-dom";
import ConsultaAxios from "./pages/ConsultaAxios.tsx";

function App() {

  return (
    <>
      <Routes>
        <Route path={"/"} element={<ListaGeneralFetch/>}/>
          <Route path={"/consultaAxios"} element={<ConsultaAxios/>}/>
      </Routes>
    </>
  )
}

export default App
