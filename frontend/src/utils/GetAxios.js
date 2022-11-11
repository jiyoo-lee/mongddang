import axios from "axios";

export const GetAxios = (url, requestData, callback) => axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  headers: {
    "Authorization": sessionStorage.getItem("token")
  }
}).get(url, requestData)
.then(response => {
    if (response.data.success) {
        callback(response.data);
    } else {
        callback(response.data.data);
    }
})
.catch(error=>{
    alert(error);
});

export default GetAxios;