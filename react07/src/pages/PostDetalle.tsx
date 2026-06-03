import {Link, useParams} from "react-router-dom";
import axios from "axios";
import type {Post} from "../models/Post.ts";
import {useEffect, useState} from "react";
import {Box, Button, Card, CardActions, CardContent, TextField, Typography} from "@mui/material";


function PostDetalle() {

    const url = "https://jsonplaceholder.typicode.com/posts/";
    const {id} = useParams<{ id: string }>();
    const [post, setPost] = useState<Post>({id: 0, title: "", body: ""});


    useEffect(() => {
        //"https://jsonplaceholder.typicode.com/posts/" + id
        axios.get<Post>(`${url}/${id}`)
            .then(response => {
                setPost(response.data);

            }).catch(error => {
            alert(error.message)
        })

    }, [id]);


    //Esto hace EL POST
    const handleGuardar = () => {
        axios.post<Post>(`${url}`,post)
            .then(response => {
                //Aqui se hace el post
                setPost(response.data);
            }).catch(error => {   alert(error)})

    }

    return (
        <>
            <Button variant="contained" component={Link} to="/posts" sx={{margin: "20px auto"}}>
                Volver atras
            </Button>

            <Box sx={{maxWidth: 600, margin: "20px auto", px: 2}}>
                <Card variant={"outlined"} sx={{borderRadius: 3}}>
                    <CardContent sx={{display: "flex", flexDirection: "column"}}>
                        <Box sx={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                            <Typography variant={"h6"} component={"h2"} sx={{fontWeight: "bold"}}>
                                Editar Post
                            </Typography>
                            <Typography variant={"h6"} component={"h2"} sx={{fontWeight: "bold", margin: "5px auto"}}>
                                <b>ID: {post.id}</b>
                            </Typography>
                        </Box>

                        <TextField
                            label={"Titulo del post"}
                            variant={"outlined"}
                            value={post.title}
                            fullWidth
                            //Esta copiando todos los atributos, y esta sobreescribiendo el title
                            onChange={e => setPost({...post, title: e.target.value})}
                        />
                        <TextField
                            label={"Contenido"}
                            variant={"outlined"}
                            value={post.body}
                            fullWidth
                            multiline
                            rows={4}
                            //Esto hace lo mismo PERO mas largo y tedioso
                            onChange={e => setPost({id: post.id, title: post.title, body: e.target.value})}
                        />

                    </CardContent>

                    <CardActions sx={{justifyContent: "flex-start", px: 4, pb: 4}}>
                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            component={Link}
                            to={"/posts"}>
                            Regresar
                        </Button>

                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            onClick={handleGuardar}
                        >
                            Guardar
                        </Button>

                        <Button variant="contained" color="secondary" size="large" component={Link} to={`/posts/${post.id}/comments`} sx={{margin: "20px auto"}}>
                            Ver comentarios
                        </Button>
                    </CardActions>
                </Card>
            </Box>
        </>
    )
}

export default PostDetalle
