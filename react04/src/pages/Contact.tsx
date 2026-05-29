import {useParams} from "react-router-dom";

function Contact () {

    const {name} = useParams<{name: string}>()

    return (
        <>
            <h2>Contact:<b>{name}</b></h2>
        </>
    )
}

export default Contact