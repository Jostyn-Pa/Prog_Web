import InicioBlog from "./pages/InicioBlog.tsx";
import {Route, Routes} from "react-router-dom";
import DetallePost from "./pages/DetallePost.tsx";

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<InicioBlog/>}/>
        <Route path="/posts/:id" element={<DetallePost/>}/>
      </Routes>
    </>
  )
}

export default App
