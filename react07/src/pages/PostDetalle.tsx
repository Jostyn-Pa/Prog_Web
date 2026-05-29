import {Link, useParams} from "react-router-dom";
import axios from "axios";
import type {Posts} from "../models/Posts.ts";
import {useEffect, useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Divider, TextField, Typography} from "@mui/material";

interface Comentario {
    id: number;
    name: string;
    email: string;
    body: string;
}

function PostDetalle() {

    const url = "https://jsonplaceholder.typicode.com/posts/"

    const {id} = useParams<{id: string}>();
    const[post, setPost] = useState<Posts>({id:9, title:"", body:""})

    const [comentarios, setComentarios] = useState<Comentario[]>([]);
    const [mostrarComentarios, setMostrarComentarios] = useState(false);

    //POST
    const handleGuardar = () => {
        axios.post<Posts>(`${url}`, post)
            .then(response => {
                //Hacer algo con el post
                setPost(response.data)
            }).catch(error => alert(error))
    };

    useEffect(() => {
        axios.get<Posts>(`${url}${id}`)
            .then(response => {
                setPost(response.data)
            }).catch(error => alert(error))

        axios.get<Comentario[]>(`${url}${id}/comments`)
            .then(response => {
                setComentarios(response.data);
            }).catch(error => alert(error));
    }, [id])

    return (
        <>
            <div>{post.title}</div>

            <Button variant="contained" component={Link} to="/posts">
                Volver a Posts
            </Button>

            <Box sx={{maxWidth: 600, margin: "20px auto", px:2}}>
                <Card variant={"outlined"} sx={{borderRadius: 3}}>
                    <CardContent sx={{ display: "flex", flexDirection: "column", gap: 3, p:4}}>

                        <Box sx={{display: "flex", justifyContent: "space-between", alignItems: "center"}}>
                            <Typography variant="h6" component="h2" sx={{fontWeight: "bold"}}>
                                Editar Post
                            </Typography>
                            <Typography variant="caption" color="text.secondary" sx={{fontWeight: "bold"}}>
                                <b>ID: {post.id}</b>
                            </Typography>
                        </Box>

                        <TextField
                            label={"Titulo del post"}
                            variant={"outlined"}
                            value={post.title}
                            fullWidth
                            onChange={(e) => setPost({...post, title: e.target.value})}
                        />

                        <TextField
                            label={"Contenido"}
                            variant={"outlined"}
                            value={post.body}
                            fullWidth
                            multiline
                            rows={4}
                            onChange={(e) => setPost({...post, body: e.target.value})}
                        />

                    </CardContent>

                    <CardActions sx={{justifyContent: "flex-start", px:4, pb:4}}>

                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            onClick={handleGuardar}
                        >
                            Guardar
                        </Button>

                        <Button
                            variant="contained"
                            color="info"
                            size="large"
                            onClick={() => setMostrarComentarios(!mostrarComentarios)}
                        >
                            {mostrarComentarios ? "Ocultar Comentarios" : "Ver Comentarios"}
                        </Button>

                        <Button
                            variant="outlined"
                            color="primary"
                            size="large"
                            component={Link}
                            to="/posts"
                        >
                            Regresar
                        </Button>
                    </CardActions>
                    {mostrarComentarios && (
                        <Box sx={{ px: 4, pb: 4 }}>
                            <Divider sx={{ my: 2 }} />
                            <Typography variant="h6" sx={{ mb: 2, fontWeight: 'bold' }}>
                                Comentarios ({comentarios.length})
                            </Typography>

                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                                {comentarios.map(comment => (
                                    <Box key={comment.id} sx={{ bgcolor: '#f9f9f9', p: 2, borderRadius: 2, border: '1px solid #eee' }}>
                                        <Typography variant="subtitle2" color="secondary" sx={{ fontWeight: 'bold' }}>
                                            {comment.name} — <span style={{ fontWeight: 'normal', color: 'gray' }}>{comment.email}</span>
                                        </Typography>
                                        <Typography variant="body2" sx={{ mt: 0.5 }}>
                                            {comment.body}
                                        </Typography>
                                    </Box>
                                ))}
                            </Box>
                        </Box>
                    )}
                </Card>
            </Box>
        </>
    )
}

export default PostDetalle