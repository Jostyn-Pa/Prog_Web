import {Route, Routes} from "react-router-dom";
import FormularioSoporte from "./pages/FormularioSoporte.tsx";
import TicketEnviado from "./pages/TicketEnviado.tsx";

function App() {

  return (
    <>
      <Routes>
        <Route path={"/"} element={<FormularioSoporte/>}/>
        <Route path={"/ticket-enviado"} element={<TicketEnviado/>}/>
      </Routes>
    </>
  )
}

export default App
