import {Link, Route, Routes} from "react-router-dom";
import Perfil from "./pages/Perfil.tsx";

function App() {

  return (
    <>
        <nav>
            <Link to="/perfil/Ana">Ver perfil de Ana</Link>
            |
            <Link to="/perfil/Pedro">Ver Perfil de Pedro</Link>
        </nav>
      <Routes>
        <Route path="/perfil/:nombreUsuario" element={<Perfil/>}/>
      </Routes>
    </>
  )
}

export default App
