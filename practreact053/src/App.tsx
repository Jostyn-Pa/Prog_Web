import {Link, Route, Routes} from "react-router-dom";
import DetallePelicula from "./pages/DetallePelicula.tsx";

function App() {

  return (
    <>
      <nav>
        <Link to={"/pelicula/Inception"}>Ver Inception</Link>
          |
        <Link to={"/pelicula/Matrix"}>Ver Matrix</Link>
      </nav>

      <Routes>
        <Route path={"/pelicula/:nombrePelicula"} element={<DetallePelicula/>}/>
      </Routes>
    </>
  )
}

export default App
