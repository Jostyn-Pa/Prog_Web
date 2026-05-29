
import './App.css'
import MyForm from "./MyForm.tsx";
import MyForm2 from "./MyForm2.tsx";
import FormularioRegistro from "./EjerciciosConjuntos/FormularioRegistro.tsx";
import FormularioProducto from "./EjerciciosConjuntos/FormularioProducto.tsx";
import FormularioResena from "./EjerciciosConjuntos/FormularioResena.tsx";
import FormularioPostulacion from "./EjerciciosConjuntos/FormularioPostulacion.tsx";

function App() {

  return (
    <>
      <MyForm/>
      <MyForm2/>
        <FormularioRegistro/>
        <FormularioProducto/>
        <FormularioResena/>
        <FormularioPostulacion/>
    </>
  )
}

export default App
