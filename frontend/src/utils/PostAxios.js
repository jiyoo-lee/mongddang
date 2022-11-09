import axios from "axios";

export const PostAxios = (url, requestData, callback) => axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  headers: {
    "Content-Type": "application/json",
  }
}).post(url, requestData)
.then(response => {
    console.log(response);
    if (response.data.success) {
        callback();
    } else {
        alert(response.data.data);
    }
})
.catch(error=>{
    alert(error);
});
