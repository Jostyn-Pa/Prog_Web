import {useParams} from "react-router-dom";

function Contact () {

    const {name} = useParams<{name:string}>()

    return (
        <>
            <h3>Contact: <b>{name}</b></h3>
        </>
    )
}

export default Contact