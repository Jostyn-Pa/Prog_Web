import {Route, Routes} from "react-router-dom";
import GetProductos from "./pages/GetProductos.tsx";
import PostProductos from "./pages/PostProductos.tsx";
import PutProductos from "./pages/PutProductos.tsx";

function App () {
  return (
      <>
          <Routes>
              <Route path={"/"} element={<GetProductos/>}/>
              <Route path={"/post"} element={<PostProductos/>}/>
              <Route path={"/put"} element={<PutProductos/>}/>
          </Routes>
      </>
  )
}
export default App