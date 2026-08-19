import { Link, useParams } from "react-router-dom";
import type { Posts } from "../models/Posts.ts";
import { useEffect, useState } from "react";
import { Box, Button, Card, CardActions, CardContent, TextField, Typography } from "@mui/material";

function PostDetalle() {

    const url = "https://jsonplaceholder.typicode.com/posts";
    const { id } = useParams<{ id: string }>();

    // Esqueleto inicial con userId
    const [post, setPost] = useState<Posts>({ userId: 0, id: 0, title: "", body: "" });
    const [loading, setLoading] = useState(false);

    // GET: Traer los datos al cargar la pantalla
    useEffect(() => {
        setLoading(true);

        fetch(`${url}/${id}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json" // Opcional en GET, pero buena práctica
            }
        })
            .then(response => response.json()) // ⚠️ Paso extra en Fetch: Convertir la respuesta a JSON
            .then(data => {
                setPost(data);
            })
            .catch(error => alert(error.message))
            .finally(() => setLoading(false));

    }, [id]);

    const handleGuardar = () => {
        setLoading(true);

        // ⚠️ CAMBIO CLAVE: Quitamos el /${id} de la URL.
        // Para CREAR, siempre se apunta a la ruta principal de la "caja" (colección).
        fetch("https://jsonplaceholder.typicode.com/posts", {
            method: "POST", // ⚠️ CAMBIO CLAVE: Usamos POST para crear uno nuevo
            headers: {
                "Content-Type": "application/json"
            },
            // Empaquetamos los datos que el usuario escribió
            body: JSON.stringify({
                title: post.title,
                body: post.body,
                userId: post.userId || 1 // Mantenemos el userId
            })
        })
            .then(response => response.json())
            .then(data => {
                // 'data' ahora trae el objeto nuevo con el ID que la API le sumó (normalmente 101)
                setPost(data);
                alert(`¡Nuevo post creado! La base de datos le asignó el ID: ${data.id}`);
            })
            .catch(error => alert(error.message))
            .finally(() => setLoading(false));
    }

    return (
        <>
            <Button variant="contained" component={Link} to="/posts" sx={{ margin: "20px auto" }}>
                Volver atrás
            </Button>

            <Box sx={{ maxWidth: 600, margin: "20px auto", px: 2 }}>
                <Card variant={"outlined"} sx={{ borderRadius: 3 }}>
                    <CardContent sx={{ display: "flex", flexDirection: "column" }}>
                        <Box sx={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                            <Typography variant={"h6"} component={"h2"} sx={{ fontWeight: "bold" }}>
                                Editar Post
                            </Typography>
                            <Typography variant={"h6"} component={"h2"} sx={{ fontWeight: "bold", margin: "5px auto" }}>
                                <b>ID: {post.id}</b>
                            </Typography>
                        </Box>

                        <TextField
                            label={"Titulo del post"}
                            variant={"outlined"}
                            value={post.title}
                            fullWidth
                            // Operador Spread para copiar el objeto y solo cambiar el título
                            onChange={e => setPost({ ...post, title: e.target.value })}
                            sx={{ mb: 2, mt: 2 }} // Un poco de margen para que no se peguen
                        />
                        <TextField
                            label={"Contenido"}
                            variant={"outlined"}
                            value={post.body}
                            fullWidth
                            multiline
                            rows={4}
                            onChange={e => setPost({ ...post, body: e.target.value })}
                        />

                    </CardContent>

                    <CardActions sx={{ justifyContent: "flex-start", px: 4, pb: 4 }}>
                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            component={Link}
                            to={"/posts"}
                        >
                            Regresar
                        </Button>

                        <Button
                            variant="contained"
                            color="success" // Le cambié el color a verde (success) para diferenciar el botón de guardar
                            size="large"
                            onClick={handleGuardar}
                            disabled={loading} // Magia visual: Desactiva el botón si está cargando
                        >
                            {loading ? "Guardando..." : "Guardar"}
                        </Button>

                        <Button
                            variant="contained"
                            color="secondary"
                            size="large"
                            component={Link}
                            to={`/posts/${post.id}/comments`}
                            sx={{ margin: "20px auto" }}
                        >
                            Ver comentarios
                        </Button>
                    </CardActions>
                </Card>
            </Box>
        </>
    )
}

export default PostDetalle;