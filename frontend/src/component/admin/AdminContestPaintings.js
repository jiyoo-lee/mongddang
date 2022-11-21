import { useState,useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";


const AdminContestPaintings = () =>{

    const navigate = useNavigate();
    const {contestId} = useParams();
    const [ paintings, setPaintings ] = useState([]);
    const [ keyword, setKeyword ] = useState("");


    useEffect(()=>{
        GetAxios('/arts-center/'+contestId+'/painting',{params:{}},(res)=>{
            setPaintings(res.data)
           
        })
    },[]) 
    
    const onChange = (e) =>{
        setKeyword(e.target.value)
    }

    const onSearch = () => {
        GetAxios('/admin/paintings/'+keyword,{params:{}},(res)=>{setPaintings(res.data)})
    }

    const onDelete = (paintingId) => {
        DeleteAxios('/admin/painting/'+paintingId,{params:{}},(res)=>{
            alert("삭제 처리 되었습니다.")
            window.location.reload()})
    }

    return (
    <div className="admin">
        <ul className="admin_wrapper">
        <div className="logo_box">
        <img className='logo' src={process.env.PUBLIC_URL + `.././img/present_logo.png`} 
                onClick={()=> {navigate('/admin')}} alt='logo'/>
                </div>
                <li className="deth_menu"> Member </li>
                <li className="admin_menu" onClick={()=>navigate('/admin')}> 회원 조회 </li>
                <hr/>
                <li className="deth_menu"> Painting </li>
                <li className="admin_menu" onClick={()=>window.location.reload()} > 그림 조회 </li>
                <hr/>
                <li className="deth_menu"> Contest </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/arts-center')}> 공모전 관리 </li>
                <li className="admin_menu" onClick={()=>
                                                {sessionStorage.clear(); navigate('/'); }
                }> 로그아웃 </li>
        </ul>

    <section className="notice">
    <div className="page-title">
        <div className="container">
            <h3>공모전 조회</h3>
        </div>
    </div>

    <div id="board-list">
        <div className="container">
            <table className="board-table">
                <thead>
                <tr>
                    <th scope="col" className="th-num">아이디</th>
                    <th scope="col" className="th-num">작성자</th>
                    <th scope="col" className="th-title">제목</th>
                    <th scope="col" className="th-date">그림</th>
                    <th scope="col" className="th-date">내용</th>
                    <th scope="col" className="th-date">좋아요 개수</th>
                    <th scope="col" className="th-date">작성일자</th>
                    <th scope="col" className="th-date">삭제</th>
                </tr>
                </thead>
                <tbody>
                {paintings.length > 0 ? paintings.map(painting => (
                    
                    <tr key={painting.paintingId}>
                        <td>{painting.memberId}</td>
                        <td>
                        {painting.nickname}
                        </td>
                        <td>{painting.title}</td>
                        <td><img className="items_img" src={painting.contestPaintingUrl}/></td>
                        <td>{painting.description}</td>
                        <td>{painting.mongddangCount}</td>
                        <td>{painting.createDatetime}</td>
                        <td><button onClick={()=>onDelete(painting.paintingId)}> 삭제 </button> </td>
                </tr>
                
                )): <></>}
        
                </tbody>
            </table>
        </div>
    </div>

    </section>
    </div>
);
}

export default AdminContestPaintings;