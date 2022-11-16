import { useNavigate } from "react-router-dom";
import PostAxios from "../../../utils/axios/PostAxios";
import getAxiosfn from "../../../utils/axios/getAxiosFn";
import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";


const MyUpload = () => {

    const navigate = useNavigate();
    const [drops, setDrops] = useState([]);
    const userId = sessionStorage.getItem("userId");

    useEffect(()=> {
        GetAxios('/drawer/list/'+userId, {params:{}},(res)=>{
            setDrops(res.data)
        })
    },[])
    
    const onCreate = () => {
        getAxiosfn('/genre', {param:{}})
        .then(res=>{
            navigate("/home/upload/new-drops", {state:{options: res}});
        });
    }

    const onUpload= (dropsId) => {
        getAxiosfn('/genre', {param:{}})
        .then(res=>{
            navigate('/home/upload/painting', {
                state: {
                    options: res,
                    dropsId: dropsId
                }
            });
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
                {
                    drops && drops.map(drop => (
                        <img className="drops" key={drop.id}
                        src={drop.lastPaintingUrl === null?process.env.PUBLIC_URL + `../.././img/no_image_.png`: drop.lastPaintingUrl} onClick={()=>onUpload(drop.id)}/>
                    ))
                }
                
            </div>
        </>
    );
}

export default MyUpload;