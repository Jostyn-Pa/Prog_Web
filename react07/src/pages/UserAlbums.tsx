import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Typography, Button, Box, Card, CardContent, TextField, CardActions } from "@mui/material";
import axios from "axios";
import type { Album } from "../models/Album.ts";

function UserAlbums() {
    const { id } = useParams<{ id: string }>();
    const [albums, setAlbums] = useState<Album[]>([]);
    const [newTitle, setNewTitle] = useState("");

    const fetchAlbums = () => {
        axios.get<Album[]>(`https://jsonplaceholder.typicode.com/users/${id}/albums`)
            .then((response) => {
                setAlbums(response.data);
            })
            .catch((error) => alert(error));
    };

    useEffect(() => {
        fetchAlbums();
    }, [id]);

    const handleGuardar = (album: Album) => {
        axios.put(`https://jsonplaceholder.typicode.com/albums/${album.id}`, album)
            .then(() => {
                alert("Álbum actualizado");
            })
            .catch((error) => alert(error));
    };

    const handleCrear = () => {
        if (!newTitle.trim()) {
            alert("Por favor, llena el campo de título");
            return;
        }

        const siguienteId = albums.length > 0 ? Math.max(...albums.map(a => a.id)) + 1 : 1;

        const nuevoAlbum: Album = {
            userId: Number(id),
            id: siguienteId,
            title: newTitle
        };

        setAlbums([nuevoAlbum, ...albums]);
        setNewTitle("");
        alert("Álbum creado");
    };

    const handleEliminar = (albumId: number) => {
        axios.delete(`https://jsonplaceholder.typicode.com/albums/${albumId}`)
            .then(() => {
                alert("Álbum eliminado");
                setAlbums(albums.filter(a => a.id !== albumId));
            })
            .catch((error) => alert(error));
    };

    const handleInputChange = (index: number, field: keyof Album, value: string) => {
        const updatedAlbums = [...albums];
        updatedAlbums[index] = { ...updatedAlbums[index], [field]: value };
        setAlbums(updatedAlbums);
    };

    return (
        <>
            <Typography variant="h4" gutterBottom sx={{ mt: 5, textAlign: "center" }}>
                Álbumes de el id: {id}
            </Typography>

            <Box sx={{ display: "flex", flexDirection: "column", gap: 3, maxWidth: 800, margin: "20px auto", px: 2 }}>

                <Card variant="outlined" sx={{ borderRadius: 3, bgcolor: "#fafafa", borderStyle: "dashed", borderWidth: 2 }}>
                    <CardContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
                        <Typography variant="h6" sx={{ fontWeight: "bold", color: "primary.main" }}>
                            Crear Nuevo Álbum
                        </Typography>
                        <TextField
                            label="Título del nuevo álbum"
                            variant="outlined"
                            fullWidth
                            value={newTitle}
                            onChange={(e) => setNewTitle(e.target.value)}
                        />
                    </CardContent>
                    <CardActions sx={{ px: 2, pb: 2 }}>
                        <Button variant="contained" color="success" onClick={handleCrear}>
                            Agregar Álbum
                        </Button>
                    </CardActions>
                </Card>

                {albums.map((album, index) => (
                    <Card key={album.id} variant="outlined" sx={{ borderRadius: 3 }}>
                        <CardContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
                            <Typography variant="h6" sx={{ fontWeight: "bold" }}>
                                Álbum ID: {album.id}
                            </Typography>

                            <TextField
                                label="Título del Álbum"
                                variant="outlined"
                                fullWidth
                                value={album.title}
                                onChange={(e) => handleInputChange(index, "title", e.target.value)}
                            />
                        </CardContent>
                        <CardActions sx={{ px: 2, pb: 2, justifyContent: "space-between" }}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleGuardar(album)}
                            >
                                Guardar Álbum {album.id}
                            </Button>

                            <Button
                                variant="outlined"
                                color="error"
                                onClick={() => handleEliminar(album.id)}
                            >
                                Eliminar
                            </Button>
                        </CardActions>
                    </Card>
                ))}
            </Box>

            <Box sx={{ display: "flex", justifyContent: "center", mb: 5 }}>
                <Button variant="contained" color="secondary" component={Link} to="/users" size="large">
                    Regresar a Usuarios
                </Button>
            </Box>
        </>
    );
}

export default UserAlbums;