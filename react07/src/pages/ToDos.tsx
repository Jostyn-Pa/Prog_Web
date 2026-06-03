import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { Typography, Button, Box, Card, CardContent, TextField, CardActions, MenuItem } from "@mui/material";
import axios from "axios";
import type { ToDo } from "../models/ToDo.ts";

function ToDos() {
    const { id } = useParams<{ id: string }>();
    const [todos, setTodos] = useState<ToDo[]>([]);
    const [newTitle, setNewTitle] = useState("");
    const [newCompleted, setNewCompleted] = useState("false");

    const fetchToDos = () => {
        axios.get<ToDo[]>(`https://jsonplaceholder.typicode.com/users/${id}/todos`)
            .then((response) => {
                setTodos(response.data);
            })
            .catch((error) => alert(error));
    };

    useEffect(() => {
        fetchToDos();
    }, [id]);

    const handleGuardar = (todo: ToDo) => {
        axios.put(`https://jsonplaceholder.typicode.com/todos/${todo.id}`, todo)
            .then(() => {
                alert("Tarea actualizada");
            })
            .catch((error) => alert(error));
    };

    const handleCrear = () => {
        if (!newTitle.trim()) {
            alert("Por favor, llena el campo de título");
            return;
        }

        const siguienteId = todos.length > 0 ? Math.max(...todos.map(t => t.id)) + 1 : 1;

        const nuevoTodo: ToDo = {
            userId: Number(id),
            id: siguienteId,
            title: newTitle,
            completed: newCompleted === "true"
        };

        setTodos([nuevoTodo, ...todos]);
        setNewTitle("");
        setNewCompleted("false");
        alert("Tarea creada");
    };

    const handleEliminar = (todoId: number) => {
        axios.delete(`https://jsonplaceholder.typicode.com/todos/${todoId}`)
            .then(() => {
                alert("Tarea eliminada");
                setTodos(todos.filter(t => t.id !== todoId));
            })
            .catch((error) => alert(error));
    };

    const handleInputChange = (index: number, field: keyof ToDo, value: any) => {
        const updatedTodos = [...todos];
        updatedTodos[index] = { ...updatedTodos[index], [field]: value };
        setTodos(updatedTodos);
    };

    return (
        <>
            <Typography variant="h4" gutterBottom sx={{ mt: 5, textAlign: "center" }}>
                To do de el id: {id}
            </Typography>

            <Box sx={{ display: "flex", flexDirection: "column", gap: 3, maxWidth: 800, margin: "20px auto", px: 2 }}>

                <Card variant="outlined" sx={{ borderRadius: 3, bgcolor: "#fafafa", borderStyle: "dashed", borderWidth: 2 }}>
                    <CardContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
                        <Typography variant="h6" sx={{ fontWeight: "bold", color: "primary.main" }}>
                            Crear Nueva Tarea
                        </Typography>
                        <TextField
                            label="Título de la nueva tarea"
                            variant="outlined"
                            fullWidth
                            value={newTitle}
                            onChange={(e) => setNewTitle(e.target.value)}
                        />
                        <TextField
                            select
                            label="Estado inicial"
                            variant="outlined"
                            fullWidth
                            value={newCompleted}
                            onChange={(e) => setNewCompleted(e.target.value)}
                        >
                            <MenuItem value="true">Completado</MenuItem>
                            <MenuItem value="false">Pendiente</MenuItem>
                        </TextField>
                    </CardContent>
                    <CardActions sx={{ px: 2, pb: 2 }}>
                        <Button variant="contained" color="success" onClick={handleCrear}>
                            Agregar Tarea
                        </Button>
                    </CardActions>
                </Card>

                {todos.map((todo, index) => (
                    <Card key={todo.id} variant="outlined" sx={{ borderRadius: 3 }}>
                        <CardContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
                            <Typography variant="h6" sx={{ fontWeight: "bold" }}>
                                Tarea ID: {todo.id}
                            </Typography>

                            <TextField
                                label="Título"
                                variant="outlined"
                                fullWidth
                                value={todo.title}
                                onChange={(e) => handleInputChange(index, "title", e.target.value)}
                            />

                            <TextField
                                select
                                label="Estado"
                                variant="outlined"
                                fullWidth
                                value={todo.completed ? "true" : "false"}
                                onChange={(e) => handleInputChange(index, "completed", e.target.value === "true")}
                            >
                                <MenuItem value="true">Completado</MenuItem>
                                <MenuItem value="false">Pendiente</MenuItem>
                            </TextField>
                        </CardContent>
                        <CardActions sx={{ px: 2, pb: 2, justifyContent: "space-between" }}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleGuardar(todo)}
                            >
                                Guardar Tarea {todo.id}
                            </Button>

                            <Button
                                variant="outlined"
                                color="error"
                                onClick={() => handleEliminar(todo.id)}
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

export default ToDos;