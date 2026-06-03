import {useEffect, useState} from "react";
import {Button, Container, Table, TableBody, TableCell, TableHead, TableRow, Typography} from "@mui/material";
import axios from "axios";
import { Link } from "react-router-dom";
import type {User} from "../models/User.ts";


function Users() {

    const [users, setUsers] = useState<User[]>([]);
    const [loading, setLoading] = useState(false);

    const fetchUsers = () => {
        setLoading(true);
        axios.get<User[]>("https://jsonplaceholder.typicode.com/users")
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => alert(error))
            .finally(() => setLoading(false));
    };

    useEffect(() => {

        fetchUsers();
    },[]);

    return (
        <>
            <Container sx={{mt: 5}}>
                <Typography variant="h4" gutterBottom>
                    Usuarios
                </Typography>

                <Table sx={{mt: 2}}>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Accion</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            users.map((user) => (
                                <TableRow key={user.id}>
                                    <TableCell>{user.id}</TableCell>
                                    <TableCell>{user.name}</TableCell>
                                    <TableCell>{user.email}</TableCell>
                                    <TableCell>
                                        <Button color={"secondary"} variant="outlined" component={Link} to={`/users/${user.id}`}>
                                            Ver detalles del usuario
                                        </Button>
                                        <Button color={"secondary"} variant="outlined" component={Link} to={`/users/${user.id}/posts`}>
                                            Posts
                                        </Button>
                                        <Button color={"secondary"} variant="outlined" component={Link} to={`/users/${user.id}/todos`}>
                                            To Do
                                        </Button>
                                        <Button color={"secondary"} variant="outlined" component={Link} to={`/users/${user.id}/albums`}>
                                            Albums
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </Container>
        </>
    )
}

export default Users;