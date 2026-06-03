import {Route, Routes} from "react-router-dom";
import Users from "./pages/Users.tsx";
import PostDetalle from "./pages/PostDetalle.tsx";
import Home from "./pages/Home.tsx";
import About from "./pages/About.tsx";
import Posts from "./pages/Posts.tsx";
import Comments from "./pages/Comments.tsx";
import UserDetail from "./pages/UserDetail.tsx";
import ToDos from "./pages/ToDos.tsx";
import UserAlbums from "./pages/UserAlbums.tsx";
import UserPosts from "./pages/UserPosts.tsx";
import NavBar from "./components/NavBar.tsx";

function App() {

  return (
    <>
      <NavBar/>

      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/about" element={<About/>}/>
        <Route path="/posts" element={<Posts/>}/>
        <Route path="/posts/:id" element={<PostDetalle/>}/>
        <Route path="/posts/:id/comments" element={<Comments/>}/>
        <Route path="/users" element={<Users/>}/>
        <Route path="/users/:id" element={<UserDetail/>}/>
        <Route path="/users/:id/posts" element={<UserPosts/>}/>
        <Route path="/users/:id/todos" element={<ToDos/>}/>
        <Route path="/users/:id/albums" element={<UserAlbums/>}/>
      </Routes>
    </>
  )
}

export default App
