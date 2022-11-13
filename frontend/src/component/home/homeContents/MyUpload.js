import { useNavigate } from "react-router-dom";
import GetAxios from "../../../utils/axios/GetAxios";
import getAxiosfn from "../../../utils/axios/getAxiosFn";
import { useState } from "react";
import axios from "axios";

const MyUpload = () => {

    const navigate = useNavigate();
    const [options, setOptions] = useState([]);
    
    const onCreate = () => {
        getAxiosfn('/genre', {param:{}})
        .then(r=>{
            navigate("/home/upload/new-drops", {state:{options: r}});
        });

    }

    return (
        <>
            <div className="upload_drops">
            <span className="home_message"> 업로드 할 드랍을 선택해주세요 </span>
            <br/>
            <br/>
            <br/>
                <img className="drops" src={process.env.PUBLIC_URL + `../.././img/default_drops.png`} onClick={onCreate}/>
                <img className="drops" src= {process.env.PUBLIC_URL + `../.././img/test2.jpg`}/>
                <img className="drops" src= {process.env.PUBLIC_URL + `../.././img/cat...jpeg`}/>
                <img className="drops"src= {process.env.PUBLIC_URL + `../.././img/test6.jpeg`}/>
            </div>
        </>
    );
}

export default MyUpload;