import React, {type ChangeEvent, type FormEvent} from "react";

function FormularioResena() {
    //Primera Forma Controlada de usar Formularios --> Espía
    const [title, setTitle] = React.useState("")
    const [comment, setComment] = React.useState("")

    const handleTitle = (event: ChangeEvent<HTMLInputElement>) => {
        setTitle(event.target.value)
    }

    const handleComment = (event: ChangeEvent<HTMLInputElement>) => {
        setComment(event.target.value)
    }

    const handleSubmit = (event:FormEvent<HTMLFormElement>) => {
        alert(`${title} adicionado!`)
        event?.preventDefault()
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <div>
                    <p>{title}</p>
                    <br/>
                    Titulo: <input type='text' value={title} onChange={handleTitle}/>
                    <br/>
                    Comentario: <input type='text' value={comment} onChange={handleComment}/>
                    <br/>
                    <input type='submit' value='Enviar'/>
                </div>
            </form>
        </>
    )
}

export default FormularioResena