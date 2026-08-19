import {AppBar, Button, Toolbar, Typography} from "@mui/material";
import {Link} from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";
import HelpIcon from "@mui/icons-material/Help";
import MdPhone from "@mui/icons-material/Phone"
import PeopleIcon from "@mui/icons-material/People";

function NavBar () {
    return (
        <>
            <AppBar position={'static'}>
                <Toolbar>
                    <Typography variant='h6' sx={{flexGrow: 1}}>
                        Mi Aplicación
                    </Typography>

                    <Button color='inherit' component={Link} to='/' startIcon={<HomeIcon/>}>Inicio</Button>
                    <Button color='inherit' component={Link} to='/about' startIcon={<HelpIcon/>}> Acerca De</Button>
                    <Button color='inherit' component={Link} to='/posts' startIcon={<MdPhone/>}>Posts</Button>
                    <Button color='inherit' component={Link} to='/users' startIcon={<PeopleIcon/>}>Usuarios</Button>
                </Toolbar>
            </AppBar>
        </>
    )
}

export default NavBar