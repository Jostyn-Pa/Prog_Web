import {Container, Typography} from "@mui/material";

function Home() {
    return (
        <>
            <Container sx={{mt: 5}}>
                <Typography variant="h4">
                    Bienvenido
                </Typography>

                <Typography variant="h6">
                    Aplicacion para consultar POSTs
                </Typography>
            </Container>
        </>
    )
}

export default Home;