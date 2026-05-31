import {Route, Routes} from "react-router-dom";
import RegistroProducto from "./pages/RegistroProducto.tsx";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<RegistroProducto />} />
      </Routes>
    </>
  )
}

export default App
