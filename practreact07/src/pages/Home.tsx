import {Container, Typography} from "@mui/material";

function Home () {
    return (
        <>
            <Container sx={{mt: 5}}>
                <Typography variant="h4">
                    Bienvenido a mi Aplicación
                </Typography>

                <Typography variant="h6">
                    Aplicación Para Consultar Posts, Usuarios, Detalles, etc
                </Typography>
            </Container>
        </>
    )
}

export default Home