import type {Medico} from "../models/Medico.ts";

const URL_API = "http://localhost:8080/api/medicos"

export const medicoApi = {
   //findAll
   findAll: async() : Promise<Medico[]> => {
       try {
           const response = await fetch(URL_API)
           return await response.json()
       } catch (error) {
           console.error(error)
           return []
       }

   },

   //findById
    findById: async(id:number):Promise<Medico|null> => {
       try {
           const response = await fetch(`${URL_API}/${id}`)
           if(response.status === 404) return null
           if(!response.ok) throw new Error("Could not find medico")
           return await response.json()
       } catch (error) {
           console.error(error)
           return null
       }
    },

    //post
    create: async(nuevoMedico: Medico): Promise<boolean> => {
       try {
           const response = await fetch(URL_API, {
               method: "POST",
               headers: {
                   "Content-Type": "application/json"
               },
               body: JSON.stringify(nuevoMedico)
           })
           return response.status === 201
       } catch (error) {
           console.error(error)
           return false
       }

    },

    //put
    actualizar: async(id:number, medicoActualizado:Medico):Promise<boolean> => {
       try {
           const response = await fetch(`${URL_API}/${id}`, {
               method: "PUT",
               headers: {
                   "Content-Type": "application/json"
               },
               body: JSON.stringify(medicoActualizado)
           })
           return await response.ok
       } catch (error) {
           console.error(error)
           return false
       }
    },

    //delete
    eliminar: async(id:number): Promise<boolean> => {
       try {
           const response = await fetch(`${URL_API}/${id}`, {
               method: "DELETE",
           })
           return await response.ok
       } catch (error) {
           console.error(error)
           return false
       }
    }

}