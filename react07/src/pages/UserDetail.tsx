import { Link, useParams } from "react-router-dom";
import axios from "axios";
import type { User } from "../models/User.ts";
import { useEffect, useState } from "react";
import { Box, Button, Card, CardActions, CardContent, TextField, Typography } from "@mui/material";

function UserDetail() {
    const url = "https://jsonplaceholder.typicode.com/users";
    const { id } = useParams<{ id: string }>();

    const [user, setUser] = useState<User>({
        id: 0,
        name: "",
        username: "",
        email: "",
        phone: "",
        website: "",
        address: {
            street: "",
            suite: "",
            city: "",
            zipcode: "",
            geo: { lat: "", lng: "" }
        },
        company: {
            name: "",
            catchPhrase: "",
            bs: ""
        }
    });

    useEffect(() => {
        axios.get<User>(`${url}/${id}`)
            .then(response => {
                setUser(response.data);
            })
            .catch(error => {
                alert(error.message);
            });
    }, [id]);

    const handleGuardar = () => {
        axios.put<User>(`${url}/${id}`, user)
            .then(response => {
                setUser(response.data);
                alert("Usuario guardado correctamente");
            })
            .catch(error => {
                alert(error.message);
            });
    };

    return (
        <>
            <Box sx={{ maxWidth: 800, margin: "40px auto", px: 2 }}>
                <Card variant={"outlined"} sx={{ borderRadius: 3 }}>
                    <CardContent sx={{ display: "flex", flexDirection: "column", gap: 2 }}>

                        <Box sx={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                            <Typography variant={"h6"} sx={{ fontWeight: "bold" }}>
                                Editar Detalle de Usuario
                            </Typography>
                            <Typography variant={"h6"} sx={{ fontWeight: "bold" }}>
                                ID: {user.id}
                            </Typography>
                        </Box>

                        <TextField label="Nombre" variant="outlined" value={user.name} fullWidth
                                   onChange={e => setUser({ ...user, name: e.target.value })} />

                        <TextField label="Username" variant="outlined" value={user.username} fullWidth
                                   onChange={e => setUser({ ...user, username: e.target.value })} />

                        <TextField label="Name" variant="outlined" value={user.name} fullWidth
                                   onChange={e => setUser({ ...user, name: e.target.value })} />

                        <TextField label="Email" variant="outlined" value={user.email} fullWidth
                                   onChange={e => setUser({ ...user, email: e.target.value })} />

                        <TextField label="Teléfono" variant="outlined" value={user.phone} fullWidth
                                   onChange={e => setUser({ ...user, phone: e.target.value })} />

                        <TextField label="Sitio Web" variant="outlined" value={user.website} fullWidth
                                   onChange={e => setUser({ ...user, website: e.target.value })} />

                        <Typography variant="subtitle1" sx={{ mt: 2, fontWeight: "bold" }}>Dirección</Typography>

                        <TextField label="Calle" variant="outlined" value={user.address.street} fullWidth
                                   onChange={e => setUser({ ...user, address: { ...user.address, street: e.target.value } })} />

                        <TextField label="Suite" variant="outlined" value={user.address.suite} fullWidth
                                   onChange={e => setUser({ ...user, address: { ...user.address, suite: e.target.value } })} />

                        <TextField label="Ciudad" variant="outlined" value={user.address.city} fullWidth
                                   onChange={e => setUser({ ...user, address: { ...user.address, city: e.target.value } })} />

                        <TextField label="Código Postal" variant="outlined" value={user.address.zipcode} fullWidth
                                   onChange={e => setUser({ ...user, address: { ...user.address, zipcode: e.target.value } })} />

                        <Box sx={{ display: "flex", gap: 2 }}>
                            <TextField label="Latitud" variant="outlined" value={user.address.geo.lat} fullWidth
                                       onChange={e => setUser({ ...user, address: { ...user.address, geo: { ...user.address.geo, lat: e.target.value } } })} />
                            <TextField label="Longitud" variant="outlined" value={user.address.geo.lng} fullWidth
                                       onChange={e => setUser({ ...user, address: { ...user.address, geo: { ...user.address.geo, lng: e.target.value } } })} />
                        </Box>

                        <Typography variant="subtitle1" sx={{ mt: 2, fontWeight: "bold" }}>Empresa</Typography>

                        <TextField label="Nombre Empresa" variant="outlined" value={user.company.name} fullWidth
                                   onChange={e => setUser({ ...user, company: { ...user.company, name: e.target.value } })} />

                        <TextField label="Eslogan" variant="outlined" value={user.company.catchPhrase} fullWidth
                                   onChange={e => setUser({ ...user, company: { ...user.company, catchPhrase: e.target.value } })} />

                        <TextField label="BS" variant="outlined" value={user.company.bs} fullWidth
                                   onChange={e => setUser({ ...user, company: { ...user.company, bs: e.target.value } })} />
                    </CardContent>

                    <CardActions sx={{ justifyContent: "flex-start", px: 4, pb: 4, gap: 2 }}>
                        <Button
                            variant="contained"
                            color="primary"
                            size="large"
                            component={Link}
                            to="/users"
                        >
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
                    </CardActions>
                </Card>
            </Box>
        </>
    );
}

export default UserDetail;