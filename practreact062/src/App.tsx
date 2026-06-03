import ListaGeneral from "./pages/ListaGeneral.tsx";
import {Route, Routes} from "react-router-dom";
import PerfilOptimizado from "./pages/PerfilOptimizado.tsx";

function App() {

  return (
    <>
      <Routes>
          <Route path="/" element={<ListaGeneral/>}/>
          <Route path="/perfil/:id" element={<PerfilOptimizado/>}/>
      </Routes>
    </>
  )
}

export default App
