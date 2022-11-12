import axios from "axios";

const PutAxios = (url, requestData, callback) => axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  headers: {
    "Content-Type": "application/json",
    "Authorization": sessionStorage.getItem("token")
  }
}).put(url, requestData)
.then(response => {
    console.log(response);
    if (response.data.success) {
        callback(response.data);
    } else {
        alert(response.data.data);
    }
})
.catch(error=>{
    alert(error);
});

export default PutAxios;