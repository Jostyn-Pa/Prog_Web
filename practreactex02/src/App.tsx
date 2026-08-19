import {Route, Routes} from "react-router-dom";
import FetchPage from "./pages/FetchPage.tsx";
import AxiosPage from "./pages/AxiosPage.tsx";

function App () {
  return (
      <>
          <Routes>
              <Route path={"/"} element={<FetchPage/>}></Route>
              <Route path={"/consultaAxios"} element={<AxiosPage/>}/>
          </Routes>
      </>
  )
}

export default App
