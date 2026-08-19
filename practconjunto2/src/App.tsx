import {Route, Routes} from "react-router-dom";
import Director1 from "./pages/Director1.tsx";
import Peliculas1 from "./pages/Peliculas1.tsx";

function App() {
  return (
      <>
          <Routes>
              <Route path={"/"} element={<Director1/>}/>
              <Route path={"/peliculas1"} element={<Peliculas1/>}/>
          </Routes>
      </>
  )
}
export default App