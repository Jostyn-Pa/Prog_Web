import NavBar from "./components/NavBar.tsx";
import {Route, Routes} from "react-router-dom";
import About from "./pages/About.tsx";
import Users from "./pages/Users.tsx";
import Home from "./pages/Home.tsx";
import PostsDetalles from "./pages/PostsDetalles.tsx";
import Posts from "./pages/Posts.tsx";

function App() {
  return (
    <>
      <NavBar/>

        <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/about' element={<About/>}/>
            <Route path='/posts' element={<Posts/>}/>
            <Route path='posts/:id' element={<PostsDetalles/>}/>
            <Route path='/users' element={<Users/>}/>
        </Routes>
    </>
  )
}

export default App
