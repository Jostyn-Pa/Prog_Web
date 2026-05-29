import React, { useState } from "react";

function FormularioRegistro() {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    const handleName = (event: React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value);
    }

    const handleEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(event.target.value);
    }

    // Corrección 2: El tipo de evento correcto para enviar un formulario
    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        alert(`Registrando a ${name} con email: ${email}`);
    }

    return (
        <form onSubmit={handleSubmit} style={{ border: '2px solid blue', padding: '20px' }}>
            <h4>Registro de Usuario</h4>

            {/* Mensaje dinámico en tiempo real */}
            <p>¡Hola, <b>{name}</b>!</p>

            <div>
                {/* Corrección 1: Usar value={...} para que React controle el texto */}
                Nombre: <input type='text' value={name} onChange={handleName} />
                <br/><br/>
                Email: <input type='email' value={email} onChange={handleEmail} />
                <br/><br/>
                <button type='submit'>Registrar</button>
            </div>
        </form>
    );
}

export default FormularioRegistro;