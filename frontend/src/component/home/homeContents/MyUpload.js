import { useNavigate } from "react-router-dom";
import Heart from "../../like/Heart";
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
            console.log(res.data)
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
                <div className="upload_drops_message">
                    <span className="home_message"> 업로드 할 드랍을 선택해주세요 </span>
                </div>
                <br/>
                <br/>
                <br/>
                <div className="drops_wrappers">
                
                </div>

                {drops && drops.map(drop => (
                    <div className="drops_list" key={drop.id}>
                        <img className="items_img"
                            src={drop.lastPaintingUrl === null?process.env.PUBLIC_URL + `../.././img/no_image_.png`: drop.lastPaintingUrl} onClick={()=>onUpload(drop.id)}/>
                        <br/>
                        <span className="genre">{drop.genre}</span> 
                        <br/>
                        <br/>
                        <span className="drawer_msg">{drop.name}</span>
                        <br/>
                        <br/>
                        <Heart id={drop.id} like={drop.isLike} count={drop.mongddangCount} type="drops"/>
                    </div>
                ))}
                
                <img className="items_img" src={process.env.PUBLIC_URL + `../.././img/default_drops.png`} onClick={onCreate}/>   
            </div>
        </>
    );
}

export default MyUpload;