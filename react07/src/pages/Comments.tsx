import { Button, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type { Comment } from "../models/Comment.ts";

function Comments() {

    const [comments, setComments] = useState<Comment[]>([]);
    const [loading, setLoading] = useState(false);
    const { id } = useParams();


    useEffect (() => {
        setLoading(true);

        axios.get<Comment[]>(`https://jsonplaceholder.typicode.com/posts/${id}/comments`)
            .then(response => {
                setComments(response.data);
            })
            .catch(error => alert(error))
            .finally(() => setLoading(false));
    },[id])

    return (
    <>
    <h1>Comentarios</h1>
    <Button
        variant="contained"
        color="primary"
        size="large"
        component={Link}
        to={`/posts/${id}`}>
            Regresar
    </Button>

    <Table>
        <TableHead>
            <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Name</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Body</TableCell>
            </TableRow>
        </TableHead>
        <TableBody>
            {comments.map(comment => (
                <TableRow key={comment.id}>
                    <TableCell>{comment.id}</TableCell>
                    <TableCell>{comment.name}</TableCell>
                    <TableCell>{comment.email}</TableCell>
                    <TableCell>{comment.body}</TableCell>
                </TableRow>
            ))}
        </TableBody>
    </Table>
    
    </>
    )

}

export default Comments