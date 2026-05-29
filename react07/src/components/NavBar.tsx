import { AppBar, Button, Toolbar, Typography } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import HelpIcon from "@mui/icons-material/Help";
import { Link } from "react-router-dom";

function NavBar() {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Mi aplicación
                </Typography>

                <Button color="inherit" component={Link} to="/" startIcon={<HomeIcon />}>
                    Inicio
                </Button>
                <Button color="inherit" component={Link} to="/about" startIcon={<HelpIcon />}>
                    Acerca de
                </Button>
                <Button color="inherit" component={Link} to="/posts">
                    Posts
                </Button>
            </Toolbar>
        </AppBar>
    );
}

export default NavBar;